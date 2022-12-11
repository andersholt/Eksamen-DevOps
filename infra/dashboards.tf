resource "aws_cloudwatch_dashboard" "main" {
  dashboard_name = var.candidate_id
  dashboard_body = <<DASHBOARD
{
  "widgets": [
    {
      "type": "metric",
      "x": 0,
      "y": 0,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "carts.value"
          ]
        ],
        "period": 10,
        "stat": "Maximum",
        "region": "eu-west-1",
        "title": "Amount of carts"
      }
    },
    {
      "type": "metric",
      "x": 12,
      "y": 0,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "cartsvalue.value"
          ]
        ],
        "period": 10,
        "stat": "Maximum",
        "region": "eu-west-1",
        "title": "Sum of money in carts"
      }
    },
    {
      "type": "metric",
      "x": 0,
      "y": 6,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "checkouts.count"
          ]
        ],
        "period": 10,
        "stat": "Sum",
        "region": "eu-west-1",
        "title": "Total checkouts"
      }
    },    
    {
      "type": "metric",
      "x": 12,
      "y": 6,
      "width": 12,
      "height": 6,
      "properties": {
        "metrics": [
          [
            "${var.candidate_id}",
            "checkout_latency.avg",
            "exception",
            "none",
            "method",
            "checkout",
            "class",
            "no.shoppifly.ShoppingCartController"
          ]
        ],
        "period": 10,
        "stat": "Average",
        "region": "eu-west-1",
        "title": "Average checkout latency"
      }
    }
  ]
}
DASHBOARD
}