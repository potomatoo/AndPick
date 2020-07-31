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
  "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNiIsImV4cCI6MTU5Njk4MzY1NH0.6xjY-gwjfAOEZ7-T27BnJuGm99zMZJ0pQkkxOhcFCfiHdyEvXbXcg6hy0qaNPuOH5VGAefqmr4YifUgrUS-BIw";
