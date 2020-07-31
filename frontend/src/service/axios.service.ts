import axios, { AxiosInstance } from "axios";

export class LocalAxios {
  static readonly instance: AxiosInstance = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000
  });
}

export class Axios {
  static readonly instance: AxiosInstance = axios.create({
    baseURL: "http://i3b107.p.ssafy.io:8080",
    timeout: 10000
  });
}

Axios.instance.defaults.headers.common["Authorization"] =
  "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNiIsImV4cCI6MTU5NjkzMjQ5NX0.SZLXqldePju5cbE-P7pf03P21QKO-QNxMKRPjv5R0m1rFGZCq2OlBcwtcmlVgMQSnTouEYzKvMxBvuQFzRfZ9Q";
