import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import MypageMain from "@/views/pages/MypageMain.vue";
import Home from "@/views/main/a.vue"

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },

  {
    path: "/mypage",
    name: "MypageMain",
    component: MypageMain,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
