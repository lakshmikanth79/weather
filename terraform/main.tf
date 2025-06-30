provider "local" {}

resource "local_file" "git_script" {
  filename = "${path.module}/initialize_repo.sh"
  content = <<-EOT
    #!/bin/bash
    git config --global --add safe.directory ${path.module}/..
    cd ${path.module}/..
    git init
    git add .
    git commit -m "Initial Terraform-based commit"
  EOT
  file_permission = "0755"
}

resource "null_resource" "run_git_script" {
  provisioner "local-exec" {
    command = "${path.module}/initialize_repo.sh"
  }
  depends_on = [local_file.git_script]
}
