
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

### ER API Authorization

First, in order to gain authorization to access the ER APIs, we require an authentication entity. To achieve this, we used  AWS Assume Role authentication, which enables us to obtain a session token. This session token serves as a temporary set of credentials that grants us the necessary permissions to interact with the ER APIs securely and in accordance with the defined roles and access policies.

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
    
 We are using `eu-west-1` as the region, if you are using any other region replace it in `https://<region>.amazonaws.com/`

For an in-depth understanding of "Assume Role" authentication, consult the official [AWS AssumeRole API Reference](https://docs.aws.amazon.com/STS/latest/APIReference/API_AssumeRole.html).

A successful response with `SessionToken` appears as:

```xml
<AssumeRoleResponse xmlns="https://sts.amazonaws.com/doc/2011-06-15/">
... (provided XML response) ...
</AssumeRoleResponse>
```
Use the provided `AccessKey`, `SecretKey`, and `SessionToken` for further authorization of ER APIs.

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

