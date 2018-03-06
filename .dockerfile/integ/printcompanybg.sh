#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'
import com.liferay.portal.kernel.service.*;
def company = CompanyLocalServiceUtil.getCompany(companyId);
System.out.println(company.toString());
EOF

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
  -u admin@liferay.com:set4now \
  -d input='{"companyId":20116}' \
  -d language='groovy' \
  -d script="$SCRIPT"
