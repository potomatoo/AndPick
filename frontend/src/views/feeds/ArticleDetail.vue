<template>
  <div class="mt-10" v-if="article">
    <v-container>
      <!-- <v-layout> -->
      <h3>{{ article.title }}</h3>
      <div class="subtitle-1 text--secondary">{{ article.pubDate }}</div>
      <div class="text-right">
        <v-icon @click="console.log('hi')">mdi-star-box-outline</v-icon>
        <v-icon class="ml-3" @click="console.log('hi')"
          >mdi-replay mdi-flip-h</v-icon
        >
      </div>
      <!-- </v-layout> -->
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <p :v-html="article.description">
        {{ article.description }}
      </p>
      <v-btn
        color="grey"
        outlined
        dark
        block
        tile
        @click="openPage(article.link)"
        >VISIT WEBSITE</v-btn
      >
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleDetail extends Vue {
  @feedModule.State article!: Article;

  checkArticle() {
    if (!this.article) {
      this.$router.replace({
        name: "ArticleListInRss",
        params: {
          feedname: this.$route.params.feedname,
          rssId: this.$route.params.rssId
        }
      });
    }
  }

  openPage(link: string) {
    window.open(link);
  }

  create() {
    // this.checkArticle();
  }
}
</script>

<style></style>
