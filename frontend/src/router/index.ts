import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/main/a.vue";
import Cover from "@/views/Cover.vue";
import Today from "@/views/feeds/TodayFeedList.vue";
import MypageMain from "@/views/pages/MypageMain.vue";

import SignupView from "@/views/accounts/SignupView.vue";
import LoginView from "@/views/accounts/LoginView.vue";
import LogoutView from "@/views/accounts/LogoutView.vue";
import DeleteUserView from "@/views/accounts/DeleteUserView.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/cover",
    name: "Cover",
    component: Cover,
  },
  {
    path: "/accounts/signup",
    name: "Signup",
    component: SignupView,
  },
  {
    path: "/accounts/login",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/accounts/logout",
    name: "Logout",
    component: LogoutView,
  },
  {
    path: "/accounts/delete",
    name: "DeleteUser",
    component: DeleteUserView,
  },
  {
    path: "/today",
    name: "Today",
    component: Today,
  },

  {
    path: "/mypage",
    name: "MypageMain",
    component: MypageMain,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
