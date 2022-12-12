terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }
  backend "s3" {
    bucket =  "analytics-1053"
    key    = "anho068/apprunner-a-new-state.state"
    region = "eu-west-1"
  }
}