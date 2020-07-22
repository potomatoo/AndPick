export interface Signup {
  user_email: string;
  user_name: string;
  user_password1: string;
  user_password2: string;
  user_img?: string;
}

export interface User {
  email: string;
  token: string;
  username: string;
  bio?: string;
  image?: string;
}

export interface UserSubmit {
  email: string;
  password: string;
}

export interface UserResponse {
  user: User;
}
