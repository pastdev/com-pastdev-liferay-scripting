#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants

def message = "Hello, world!"
logger.info(message)
status=BackgroundTaskConstants.STATUS_SUCCESSFUL
statusMessage=message

EOF

echo "$SCRIPT"

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
    -u admin@liferay.com:set4now \
    -d name='hellobg' \
    -d language='groovy' \
    -d script="$SCRIPT"
