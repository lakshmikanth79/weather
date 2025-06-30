#!/bin/bash

cd ..
if [ ! -d .git ]; then
  git init
  git config user.name "Lakshmikanth"
  git config user.email "lakshmikanth@example.com"
  echo "# WeatherApp Terraform Init" > README.md
  git add .
  git commit -m "Initial Terraform-based commit"
fi
