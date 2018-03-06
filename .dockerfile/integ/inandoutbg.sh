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
  -u admin@liferay.com:set4now \
  -d name='inandout_background_task' \
  -d input='{"companyId":20116}' \
  -d outputNames='["users"]' \
  -d language='groovy' \
  -d script="$SCRIPT" 2> /dev/null |
  perl -MJSON -e "print(decode_json(do {local \$/; <STDIN> })->{backgroundTaskId})"`

sleep 5

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/status \
  -u admin@liferay.com:set4now \
  -d backgroundTaskId="$BACKGROUND_TASK_ID"

