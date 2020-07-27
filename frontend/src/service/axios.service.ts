import axios, { AxiosInstance } from "axios";

export default class Axios {
  static readonly instance: AxiosInstance = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000
  });
}
