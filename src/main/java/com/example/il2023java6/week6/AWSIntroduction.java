package com.example.il2023java6.week6;


/**
 *  cloud services
 *  ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
 *  AWS
 *      regions:
 *      zone:
 *
 *  EC2, ENI[ip], EBS volume, ASG
 *  security group (on instances)
 *  cloud watch (metrics / log, cpu, disk, memory monitor)
 *
 *  ALB -> Target
 *  Route53
 *  API Gateway
 *  CloudFront (edge)
 *  VPC:
 *      1. subnet: using CIDR block (ip range)
 *      2. public subnet vs private subnet
 *
 *                  internet gateway / instance
 *                          | route table
 *                      subnet (public subnet)
 *  VPC Endpoint
 *              user
 *              |
 *          API Gateway - CloudMap (discovery service)
 *              |
 *            VPC private link
 *              |
 *          VPC(private subnet)
 *
 *  CloudMap (discovery service)
 *  OpenSearch
 *  S3(bucket, presigned url) -> S3 glacier
 *  RDS(oracle, mysql, postgresql) vs Aurora
 *  Dynamo DB
 *  KMS
 *  SQS : FIFO / standard
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  ECR
 *  ECS
 *  Code Build
 *  Code Pipeline
 *  Test + TDD
 */