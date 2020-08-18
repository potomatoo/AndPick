<template>
  <div class="mt-10" v-if="article">
    <div :class="{ left: onEdit }">
      <v-container>
        <div class="text-left">
          <add-board-menu />
          <add-scrap-menu @onPost="onPost" @onNewPost="onNewPost" />
        </div>
        <v-divider class="mt-2"></v-divider>
        <h3 class="mt-10 mb-0" style="font-family: 'Do Hyeon', sans-serif;">
          {{ article.title }}
        </h3>
        <div class="text-caption text--secondary">{{ article.pubDate }}</div>
      </v-container>

      <v-container>
        <div
          class="article-desc"
          v-html="article.description"
          :class="{ desc: !onEdit }"
        >
          {{ article.description }}
        </div>
        <div :class="{ desc: !onEdit }">
          <v-btn
            color="grey"
            outlined
            dark
            block
            tile
            @click="openPage(article.link)"
            >VISIT WEBSITE</v-btn
          >
        </div>
      </v-container>
    </div>
    <div :class="{ vl: onEdit }"></div>
    <div :class="{ right: onEdit }">
      <router-view @save="saveEdit" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article } from "../../store/Feed.interface";

import AddBoardMenu from "@/components/feeds/AddBoardMenu.vue";
import AddScrapMenu from "@/components/feeds/AddScrapMenu.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    AddBoardMenu,
    AddScrapMenu
  }
})
export default class ArticleDetail extends Vue {
  @feedModule.State article!: Article;

  onEdit = false;

  checkArticle() {
    if (!this.article) {
      this.$router.replace({
        name: "ArticleListInRss",
        params: {
          feedId: this.$route.params.feedId,
          subscribeId: this.$route.params.subscribeId
        }
      });
    }
  }

  openPage(link: string) {
    window.open(link);
  }

  mounted() {
    // 새로고침시 article state가 초기화되면 상위 페이지로 이동
    this.checkArticle();
    this.setStyle();
  }

  saveEdit() {
    this.$router.replace({
      name: "ArticleDetail",
      params: {
        feedId: this.$route.params.feedId,
        subscribeId: this.$route.params.subscribeId,
        articleId: this.$route.params.articleId
      }
    });
    this.onEdit = false;
  }

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "ArticleDetail") {
      this.onEdit = false;
    } else if (
      this.$route.name === "NewScrapInSubs" ||
      this.$route.name === "EditScrapInSubs"
    ) {
      this.onEdit = true;
    }
  }

  onPost(postId: number) {
    this.$router.push({
      name: "EditScrapInSubs",
      params: { postId: postId.toString() }
    });
  }

  onNewPost(postDirId: number) {
    this.$router.push({
      name: "NewScrapInSubs",
      params: { postDirId: postDirId.toString() }
    });
  }

  setStyle() {
    const article = document.querySelector(".article-desc");
    const images = article?.querySelectorAll("img");
    const videos = article?.querySelectorAll("video");
    const iframe = article?.querySelectorAll("iframe");
    const pTag = article?.querySelectorAll("p");
    if (images?.length) {
      images.forEach(el => el.setAttribute("style", "width: 100%"));
    }
    if (videos?.length) {
      videos.forEach(el => el.setAttribute("style", "width: 100%"));
    }
    if (iframe?.length) {
      iframe.forEach(el => el.setAttribute("style", "width: 100%"));
    }
    if (pTag?.length) {
      pTag.forEach(el => el.setAttribute("style", "margin: 24px 0px"));
    }
  }
}
</script>

<style scoped>
.left {
  width: 50%;
  float: left;
  padding-right: 40px;
}

.right {
  width: 50%;
  float: right;
  padding-left: 40px;
}

.vl {
  border-left: 4px solid lightgrey;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -2px;
}

.desc {
  margin: 30px 150px 40px 150px;
}
</style>
