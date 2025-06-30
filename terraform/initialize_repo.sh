#!/bin/bash
git config --global --add safe.directory ./..
cd ./..
git init
git add .
git commit -m "Initial Terraform-based commit"
