<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1 v-if="articleList[0]">{{ articleList[0].rssTitle }}</h1>
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
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Rss, Article } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class ArticleListInRss extends Vue {
  @feedModule.State rssList!: Rss[];
  @feedModule.State articleList!: Article[];
  @feedModule.Mutation SELECT_ARTICLE: any;
  @feedModule.Action FETCH_ARTICLE_LIST: any;

  rssTitle: string | null = null;

  // @Watch("$route", { immediate: true })
  // selectSub() {
  //   this.SELECT_SUBSCRIBE({
  //     subscribeId: this.$route.params.subscribeId
  //   });
  // }

  selectArticle(article: Article) {
    this.SELECT_ARTICLE(article);
  }

  @Watch("$route", { immediate: true })
  fetchData() {
    this.FETCH_ARTICLE_LIST(this.$route.params.subscribeId);
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
