<template>
  <v-dialog
    v-model="isActive"
    max-width="60%"
    transition="slide-x-reverse-transition"
  >
    <v-card v-if="article">
      <v-icon class="exit-btn" @click="isActive = false">mdi-close</v-icon>
      <div class="pa-15">
        <v-container>
          <h3 style="font-family: 'Do Hyeon', sans-serif;">
            {{ article.title }}
          </h3>
          <div class="text-caption text--secondary">{{ article.pubDate }}</div>
        </v-container>
        <v-divider></v-divider>
        <v-container>
          <div class="article-desc" v-html="article.description">
            {{ article.description }}
          </div>
          <div class="mt-10">
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
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Article } from "@/store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleDetailModal extends Vue {
  @feedModule.State article!: Article;

  @PropSync("modalActive", { type: Boolean }) readonly isActive!: boolean;

  openPage(link: string) {
    window.open(link);
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

  updated() {
    this.setStyle();
  }
}
</script>

<style scoped>
.exit-btn {
  position: relative;
  top: 2vh;
  left: 2vh;
}
</style>
