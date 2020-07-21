import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Profile from "../views/Profile.vue";
import Register from "../views/Register.vue";
import Settings from "../views/Settings.vue";
import Editor from "../views/Editor.vue";
import Article from "../views/Article.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: "/profile",
    name: "profile",
    component: Profile
  },
  {
    path: "/settings",
    name: "settings",
    component: Settings
  },
  {
    path: "/article",
    name: "article",
    component: Article
  },
  {
    path: "/editor",
    name: "editor",
    component: Editor
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
