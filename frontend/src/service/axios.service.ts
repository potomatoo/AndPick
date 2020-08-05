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
  "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNiIsImV4cCI6MTU5NzQ3MjcxOX0.ts86pJWkYeEB4VqB-fhQTsa8gnXhaHXEaTrrt4N-u4YOBKCYFygs3JhfTPF_wyg4GEf0zJ5lP9G3872W274TdA";
