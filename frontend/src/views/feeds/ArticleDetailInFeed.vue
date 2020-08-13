<template>
  <div class="mt-10" v-if="article">
    <div :class="{ left: onEdit }">
      <v-container>
        <h3>{{ article.title }}</h3>
        <div class="subtitle-1 text--secondary">{{ article.pubDate }}</div>
        <div class="text-right">
          <add-board-menu />
          <add-scrap-menu @onPost="onPost" @onNewPost="onNewPost" />
        </div>
      </v-container>

      <v-divider></v-divider>

      <v-container class="text-center">
        <p
          :v-html="article.description"
          :class="{ desc: !onEdit }"
          @mouseup="drag"
        >
          {{ article.description }}
        </p>
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
export default class ArticleDetailFeed extends Vue {
  @feedModule.State article!: Article;

  onEdit = false;

  selectText = "";

  checkArticle() {
    if (!this.article) {
      this.$router.replace({
        name: "Feed",
        params: {
          feedId: this.$route.params.feedId
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
  }

  drag() {
    const select = document.getSelection();
    if (select && select.toString()) {
      this.selectText = select.toString();
      console.log(select.toString());
    }
  }

  saveEdit() {
    this.$router.replace({
      name: "ArticleDetailInFeed",
      params: {
        feedId: this.$route.params.feedId,
        articleId: this.$route.params.articleId
      }
    });
    this.onEdit = false;
  }

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "ArticleDetailInFeed") {
      this.onEdit = false;
    } else if (
      this.$route.name === "NewScrapInFeed" ||
      this.$route.name === "EditScrapInFeed"
    ) {
      this.onEdit = true;
    }
  }

  onPost(postId: number) {
    this.$router.push({
      name: "EditScrapInFeed",
      params: { postId: postId.toString() }
    });
  }

  onNewPost(postDirId: number) {
    this.$router.push({
      name: "NewScrapInFeed",
      params: { postDirId: postDirId.toString() }
    });
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
