import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/main/a.vue";
import Cover from "@/views/Cover.vue";
import Today from "@/views/feeds/TodayFeedList.vue";

import Later from "@/views/feeds/ReadLaterList.vue";
import AddRss from "@/views/feeds/AddRss.vue";
import FeedPage from "@/views/feeds/FeedPage.vue";
import BoardArticleList from "@/views/feeds/BoardArticleList.vue";
import BoardArticleDetail from "@/views/feeds/BoardArticleDetail.vue";
import ArticleListInRss from "@/views/feeds/ArticleListInRss.vue";
import ArticleDetail from "@/views/feeds/ArticleDetail.vue";
import ArticleDetailInFeed from "@/views/feeds/ArticleDetailInFeed.vue";

import SignupView from "@/views/accounts/SignupView.vue";
import LoginView from "@/views/accounts/LoginView.vue";
import SocialLoginView from "@/views/accounts/SocialLoginView.vue";
import LogoutView from "@/views/accounts/LogoutView.vue";
import UpdateUserView from "@/views/accounts/UpdateUserView.vue";
import DeleteUserView from "@/views/accounts/DeleteUserView.vue";

import PostDir from "@/views/pages/PostDir.vue";
import EditArticle from "@/views/pages/EditArticle.vue";
import HashTag from "@/views/pages/HashTag.vue";
import SelectFromOutSide from "@/views/pages/SelectFromOutside.vue";
import { nextTick } from "vue/types/umd";

import MypageExplain from "@/views/explain/MypageExplain.vue";
import BoardExplain from "@/views/explain/BoardExplain.vue";
import FeedExplain from "@/views/explain/FeedExplain.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/cover",
    name: "Cover",
    component: Cover
  },
  {
    path: "/accounts/signup",
    name: "Signup",
    component: SignupView
  },
  {
    path: "/accounts/login/",
    name: "Login",
    component: LoginView
  },
  {
    path: "/accounts/social",
    name: "SocialLogin",
    component: SocialLoginView
  },
  {
    path: "/accounts/logout",
    name: "Logout",
    component: LogoutView
  },
  {
    path: "/accounts/delete",
    name: "DeleteUser",
    component: DeleteUserView
  },
  {
    path: "/accounts/update",
    name: "UpdateUser",
    component: UpdateUserView
  },
  {
    path: "/today",
    name: "Today",
    component: Today
  },
  {
    path: "/later",
    name: "Later",
    component: Later
  },
  {
    path: "/mypage/:postDirId",
    name: "PostDir",
    component: PostDir
  },

  {
    path: "/mypage/:postDirId/newpost",
    name: "NewPost",
    component: EditArticle
  },

  {
    path: "/scrap/:postDirId/newpost",
    name: "NewScrapFromGoole",
    component: EditArticle
  },

  {
    path: "/fromothersite/select/:scrapKey",
    name: "SelectFromOutside",
    component: SelectFromOutSide
  },

  {
    path: "/mypage/:postDirId/:postId/post",
    name: "EditPost",
    component: EditArticle
  },

  {
    path: "/mypage/:tagName/hashtag",
    name: "HashTag",
    component: HashTag
  },

  {
    path: "/add",
    name: "AddRss",
    component: AddRss
  },
  {
    path: "/feed/:feedId",
    name: "Feed",
    component: FeedPage
  },
  {
    path: "/feed/:feedId/subscription/:subscribeId",
    name: "ArticleListInRss",
    component: ArticleListInRss
  },
  {
    path: "/feed/:feedId/subscription/:subscribeId/:articleId",
    name: "ArticleDetail",
    component: ArticleDetail,
    children: [
      {
        path: "scrap",
        name: "NewScrapInSubs",
        component: EditArticle
      },
      {
        path: "scrap/:postId",
        name: "EditScrapInSubs",
        component: EditArticle
      }
    ]
  },
  {
    path: "/feed/:feedId/article/:articleId",
    name: "ArticleDetailInFeed",
    component: ArticleDetailInFeed,
    children: [
      {
        path: "scrap",
        name: "NewScrapInFeed",
        component: EditArticle
      },
      {
        path: "scrap/:postId",
        name: "EditScrapInFeed",
        component: EditArticle
      }
    ]
  },
  {
    path: "/board/:boardId",
    name: "BoardArticleList",
    component: BoardArticleList
  },
  {
    path: "/board/:boardId/news/:newsId",
    name: "BoardArticleDetail",
    component: BoardArticleDetail,
    children: [
      {
        path: "scrap",
        name: "NewScrapInBoard",
        component: EditArticle
      },
      {
        path: "scrap/:postId",
        name: "EditScrapInBoard",
        component: EditArticle
      }
    ]
  },
  {
    path: "/explain/mypage",
    name: "MypageExplain",
    component: MypageExplain
  },
  {
    path: "/explain/board",
    name: "BoardExplain",
    component: BoardExplain
  },
  {
    path: "/explain/feed",
    name: "FeedExplain",
    component: FeedExplain
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPositin) {
    return { x: 0, y: 0 };
  }
});

router.beforeEach((to, from, next) => {
  const publicPages = ["Cover", "Signup", "Login", "SocialLogin"];
  const authRequired = !publicPages.includes(to.name!);
  const isLogedIn = !!window.sessionStorage.getItem("jwt-token");
  const unAuthRequiredPages = ["Signup", "Login", "SocialLogin"];
  const unAuthRequired = unAuthRequiredPages.includes(to.name!);

  authRequired && !isLogedIn
    ? to.name === "Home" && !isLogedIn
      ? next({ name: "Cover" })
      : next({ name: "Login" })
    : next();
  unAuthRequired && isLogedIn ? next({ name: "Cover" }) : next();
});

export default router;
