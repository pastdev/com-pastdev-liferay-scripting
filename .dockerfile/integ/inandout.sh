#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'

import com.liferay.portal.kernel.service.*;

users = UserLocalServiceUtil.getCompanyUsers(companyId, -1, -1).size()
EOF

echo "$SCRIPT"

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
  -u admin@liferay.com:set4now \
  -d name='inandout_background_task' \
  -d input='{"companyId":20116}' \
  -d outputNames='["users"]' \
  -d language='groovy' \
  -d script="$SCRIPT"

