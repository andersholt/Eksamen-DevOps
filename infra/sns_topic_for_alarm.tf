resource "aws_cloudwatch_metric_alarm" "Too_many_checkouts" {
  alarm_name          = "to_many_checkouts"
  metric_name = "checkouts.count"
  namespace = "1053"

  comparison_operator = "GreaterThanThreshold"
  threshold = "5"
  evaluation_periods  = "3"
  period = "300"

  statistic = "Maximum"
  insufficient_data_actions = []
  alarm_actions = [aws_sns_topic.alarms.arn]
}




resource "aws_sns_topic" "alarms" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.alarms.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}