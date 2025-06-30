terraform {
  required_providers {
    null = {
      source  = "hashicorp/null"
      version = "~> 3.0"
    }
  }
}

provider "null" {}

# STEP 1: Initialize Git Repo
resource "null_resource" "git_initializer" {
  provisioner "local-exec" {
    command = <<EOT
      cd ..
      if [ ! -d .git ]; then
        git init
        git config user.name "Lakshmikanth"
        git config user.email "lakshmikanth@example.com"
        echo "# WeatherApp Terraform Init" > README.md
        git add .
        git commit -m "Initial Terraform-based commit"
      fi
    EOT
  }
}

# STEP 2: Build and Run Docker Image
resource "null_resource" "docker_build_and_run" {
  depends_on = [null_resource.git_initializer]

  provisioner "local-exec" {
    command = <<EOT
      cd ..
      docker build -t lakshmikanth79/weather-app .
      docker run -d --rm --name weather-app lakshmikanth79/weather-app || true
    EOT
  }
}

