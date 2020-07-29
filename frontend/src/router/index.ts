import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/main/a.vue";
import Today from "@/views/feeds/TodayFeedList.vue";
import FolderMain from "@/views/pages/FolderMain.vue";

import EditArticle from "@/views/pages/EditArticle.vue"

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/today",
    name: "Today",
    component: Today
  },

  {
    path: "/mypage/:pageName",
    name: "FolderMain",
    component: FolderMain,
  },

  {
    path: "/editarticle",
    name: "EditArticle",
    component: EditArticle,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
