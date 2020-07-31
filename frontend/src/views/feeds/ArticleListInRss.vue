<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1>{{ rssTitle }}</h1>
          <h1>{{ $route.params }}</h1>
        </v-flex>
        <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>
    <v-container class="text-center">
      <v-layout justify-center>
        <h4>Which sources would you like to follow?</h4>
      </v-layout>
      <v-layout justify-center>
        <p>
          You can follow publications, blogs feeds.
        </p>
      </v-layout>
      <v-btn color="success" dark @click="selectArticle(article)"
        >ADD CONTENT</v-btn
      >
    </v-container>

    <!-- 기사 리스트 -->
    <v-container class="offset-lg3 lg6">
      <v-list three-line>
        <v-list-item-group>
          <v-list-item v-for="(article, idx) in articleList" :key="idx">
            <v-list-item-content @click="selectArticle(article)">
              <router-link
                class="router-link"
                :to="{
                  name: 'ArticleDetail',
                  params: { articleId: idx }
                }"
              >
                <v-list-item-content class="mt-3">
                  <div class="h4">
                    {{ article.title }}
                  </div>
                  <div class="sumtitle-1 text--secondary">
                    {{ article.pubDate }}
                  </div>
                  <v-list-item-subtitle>
                    {{ article.description }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </router-link>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>
    <router-view />
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { RssList, Rss } from "../../store/Feed.interface";
import { Axios } from "@/service/axios.service";

const feedModule = namespace("feedModule");

@Component
export default class ArticleListInRss extends Vue {
  @feedModule.State rssList!: Rss[];
  @feedModule.Mutation selectArticle: any;

  rssTitle: string | null = null;
  articleList: [] | null = null;

  @Watch("$route", { immediate: true })
  @Watch("rssList")
  fetchArticles() {
    console.log("fetchArticle 안됨");
    // this.rssList.forEach(rss => {
    //   if (rss.id === Number(this.$route.params.rssId)) {
    //     this.rssTitle = rss.title;
    //     Axios.instance
    //       .get("/api/public/test/rss")
    //       .then(res => {
    //         this.articleList = res.data.data;
    //       })
    //       .catch(err => console.error(err));
    //   }
    // });
  }

  // created() {
  //   this.fetchArticles();
  // }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
