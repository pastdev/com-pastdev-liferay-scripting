# Liferay Scripting
This plugin provides a
[JSON web service](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/invoking-json-web-services)
that exposes the [script engine](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/using-liferays-script-engine).
It has two modes that scripts can be run in: `eval`, `spawn`.  With `eval`,
the script is executed serially and a response is returned.  With `spawn`,
the script is scheduled to be run as a background task, and a response
containing the task id is returned.  This id can be used to query for status.
Finally, a `status` enpoint is provided to query for the status of a
spawned task.

Both modes support five overloaded forms: script and language, script and 
language with inputs, script and language with outputs, script and language
with inputs and outputs, and script and language with inputs and outputs and
allowed classes.  The exact API can be inspected using the 
[`/api/jsonws` browser](http://localhost:8080/api/jsonws?contextName=pastdev_scripting).

This plugin is powerful.  It allows full access to all Liferay API's from
inside the running instance.  As such, it will only be allowed for users
who are members of the _Administrators_ group (checked by
[`getPermissionChecker().isOmniadmin()`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/)).

## `eval`
All scripts will have the following merged with their `input`:

* *`meta`:* A map containing the following request metadata:
  * *`user`:* The [`User`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/) who submitted the request
* *`logger`:* An instance of an slf4j [`Logger`](https://www.slf4j.org/apidocs/index.html)
* *`progressMonitor`:* An instance of [`ProgressMonitor`](https://github.com/pastdev/com-pastdev-liferay-scripting/blob/master/com.pastdev.liferay.scripting-service/src/main/java/com/pastdev/liferay/scripting/service/impl/ProgressMonitor.java)

Also, if no `outputNames` were specified, then:

* *`out`:* A writer whose content will be returned as the response

## `spawn`
Spawn is just like `eval` with a few additional requirements.  First, a 
`name` must be supplied as an argument to the endpoint.  This will serve
as the name of the background task.  Second, the script _must_ set the
value of `status` and `statusMessage` in the script itself.  These 
two values should be set for all branches of the script (including
failures).  Also, given that spawn runs in the background, it is not
capable of returning actual results.  As such, `out` will never be 
automatically provided.  If you need data out of spawn you
will have to use some sort of persistence mechanism and tie the data
to the background task id.  For example, you could write to a new
database table.

### Security of `spawn` arguments
For spawn to work, it has to write its arguments (script, input,
outputNames) to the database.  Given that a script or the inputs may
contain sensitive information, these 2 fields are encrypted using the
company key.

## `status`
Returns the status information from a previously `spawn`ed script.

## Examples
The following examples are presented as `bash` scripts using `curl`.  It 
should be easy enough to translate to other forms.

### hello.sh
A simple script to print `Hello, world!` to the logs:

Using `eval`:
```
#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'
logger.info("Hello, world!")
EOF

echo "$SCRIPT"

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
    -u admin@liferay.com:admin \
    -d language='groovy' \
    -d script="$SCRIPT"
```

Using `spawn`:
```
#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants

def message = "Hello, world!"
logger.info(message)
status=BackgroundTaskConstants.STATUS_SUCCESSFUL
statusMessage=message
EOF

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
    -u admin@liferay.com:admin \
    -d name='hello_background_task' \
    -d language='groovy' \
    -d script="$SCRIPT"
```

### whoami.sh
A script to return the user details for the user submitting the script:

Using `eval`:
```
#!/bin/bash
IFS='' read -r -d '' SCRIPT <<'EOF'
user = meta["user"];
EOF

curl -X POST http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
  -u admin@liferay.com:admin \
  -d outputNames='["user"]' \
  -d language='groovy' \
  -d script="$SCRIPT"
```

### logcompany.sh
Writes the name of the company matching the supplied id to the log:

Using `eval`:
```
IFS='' read -r -d '' SCRIPT <<'EOF'
import com.liferay.portal.kernel.service.*;
def company = CompanyLocalServiceUtil.getCompany(companyId);
logger.info("Company {}: {}", companyId, company.getName());
EOF

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
  -u admin@liferay.com:admin \
  -d input='{"companyId":20116}' \
  -d language='groovy' \
  -d script="$SCRIPT"
```

Using `spawn`:
```
IFS='' read -r -d '' SCRIPT <<'EOF'
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants

def company = CompanyLocalServiceUtil.getCompany(companyId);
logger.info("Company {}: {}", companyId, company.getName());

status=BackgroundTaskConstants.STATUS_SUCCESSFUL
statusMessage="Success"
EOF

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
  -u admin@liferay.com:admin \
  -d name='logcompany_background_task' \
  -d input='{"companyId":20116}' \
  -d language='groovy' \
  -d script="$SCRIPT"
```

### usersincompany.sh
Counts the number of users in the supplied company:

Using eval:
```
#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'

import com.liferay.portal.kernel.service.*;

users = UserLocalServiceUtil.getCompanyUsers(companyId, -1, -1).size()
EOF

echo "$SCRIPT"

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
  -u admin@liferay.com:admin \
  -d name='inandout_background_task' \
  -d input='{"companyId":20116}' \
  -d outputNames='["users"]' \
  -d language='groovy' \
  -d script="$SCRIPT"
```

Using spawn (puts the count in the `statusMessage`):
```
#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'

import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants

users = UserLocalServiceUtil.getCompanyUsers(companyId, -1, -1)

status=BackgroundTaskConstants.STATUS_SUCCESSFUL
statusMessage=new StringBuilder()
    .append("Found ")
    .append(users.size())
    .append(" users in company ")
    .append(companyId)
    .toString()
EOF

echo "$SCRIPT"

BACKGROUND_TASK_ID=`curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
  -u admin@liferay.com:admin \
  -d name='inandout_background_task' \
  -d input='{"companyId":20116}' \
  -d outputNames='["users"]' \
  -d language='groovy' \
  -d script="$SCRIPT" 2> /dev/null |
  perl -MJSON -e "print(decode_json(do {local \$/; <STDIN> })->{backgroundTaskId})"`

sleep 5

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/status \
  -u admin@liferay.com:admin \
  -d backgroundTaskId="$BACKGROUND_TASK_ID"
```