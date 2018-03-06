#!/bin/bash

IFS='' read -r -d '' SCRIPT <<'EOF'
user = meta["user"];
EOF

curl -X POST http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/spawn \
  -u admin@liferay.com:set4now \
  -d outputNames='["user"]' \
  -d language='groovy' \
  -d script="$SCRIPT"
