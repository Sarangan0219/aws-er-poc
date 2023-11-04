
# Entity Resolution (ER) API PoC

Entity Resolution API provides functionalities of AWS Entity Resolution. For comprehensive details, visit the [official AWS Entity Resolution documentation](https://docs.aws.amazon.com/entityresolution/latest/apireference/Welcome.html).

## Table of Contents
- [Introduction](#introduction)
- [Endpoints](#endpoints)
  - [GET ER API Authorization](#get-er-api-authorization)
  - [06-all-names-dob-no-mname-match](#06-all-names-dob-no-mname-match)

## Introduction
This document elaborates on the usage of the Entity Resolution API, showcasing sample requests. For this example, the focus is primarily on [getmatchid](https://docs.aws.amazon.com/entityresolution/latest/apireference/API_GetMatchId.html).

## Endpoints

### GET ER API Authorization

- **URL**: 
  ```
  https://sts.eu-west-1.amazonaws.com/?AUTHPARAMS&Version=2011-06-15&Action=AssumeRole&RoleArn=arn:aws:iam::004724176825:role/scv-er-poc-er-service-sbox&RoleSessionName=test
  ```
- **Method**: `GET`
- **Headers**:
  ```
  Authorization: AWS Signature
  Access Key: <access-key>
  Secret Key: <secret-key>
  ```
- **Query Parameters**: 
  - AUTHPARAMS
  - Version: 2011-06-15
  - Action: AssumeRole
  - RoleArn: arn:aws:iam::004724176825:role/scv-er-poc-er-service-sbox
  - RoleSessionName: test

#### AWS Assume Role Authentication

For an in-depth understanding of "Assume Role" authentication, consult the official [AWS AssumeRole API Reference](https://docs.aws.amazon.com/STS/latest/APIReference/API_AssumeRole.html).

A successful response appears as:

```xml
<AssumeRoleResponse xmlns="https://sts.amazonaws.com/doc/2011-06-15/">
... (provided XML response) ...
</AssumeRoleResponse>
```

Use the provided `AccessKey`, `SecretKey`, and `SessionToken` for further authorization tasks.

### 06-all-names-dob-no-mname-match

- **URL**: 
  ```
  https://entityresolution.eu-west-1.amazonaws.com/matchingworkflows/mwf1_rb_fl_sm1/matches
  ```
- **Method**: `POST`
- **Headers**:
  ```
  Authorization: AWS Signature
  Access Key: <access-key>
  Secret Key: <secret-key>
  Session Token: <session-token>
  ```
- **Body**:
  ```json
  {
    "record": {
      "fname": "b",
      "mname" : "t",
      "lname": "wayne",
      "dob": "2000-12-31"
    }
  }
  ```

**Note**: Always replace `<access-key>`, `<secret-key>`, and `<session-token>` with the actual values when making API calls.
