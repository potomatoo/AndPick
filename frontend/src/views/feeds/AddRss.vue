<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1 style="font-family: 'Do Hyeon', sans-serif;">
            새 채널을 구독해주세요.
          </h1>
        </v-flex>
      </v-layout>
    </v-container>

    <v-divider></v-divider>
    <v-container>
      <v-layout> </v-layout>
      <v-text-field
        outlined
        label="RSS 채널을 검색하세요. 카테고리 검색은 앞에 #을 붙이세요 (ex.#IT)"
        type="text"
        clearable
        prepend-inner-icon="mdi-magnify"
        v-model="inputText"
        @keypress.enter="searchRss($event)"
      ></v-text-field>
    </v-container>
    <rss-category />
    <p style="margin-left: 10px; margin-top: 4-px; font-weight: bold">
      추천하는 채널 목록
    </p>

    <v-container fluid>
      <!-- <v-row> -->
      <v-col v-for="rss in rssList" :key="rss.rss.rssId">
        <v-card max-height="250px">
          <v-card-text>
            <follow-button :rss="rss.rss" />
            <v-row>
              <v-col sm="2" class="pa-0 text-center">
                <img v-if="rss.img" :src="rss.img" class="rss-img " />
                <img v-else src="@/assets/noimage.jpg" class="rss-img" />
              </v-col>
              <v-col class="pa-0">
                <v-list-item-content class="py-0 ml-5">
                  <p
                    class="h5 text--primary font-weight-bold rss-title"
                    @click="selectRss(rss)"
                  >
                    {{ rss.title }}
                  </p>
                  <p class="mt-2 mb-4 rss-link" @click="openWeb(rss.link)">
                    {{
                      rss.link[4] === "s"
                        ? rss.link.slice(8)
                        : rss.link.slice(7)
                    }}
                  </p>
                  <div
                    class="text--primary article-title"
                    v-for="article in rss.items"
                    :key="article.link"
                    @click="toArticleDetail(article.link)"
                  >
                    - {{ article.title }}
                  </div>
                </v-list-item-content>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
      <!-- </v-row> -->
    </v-container>
    <rss-modal :rssModalActive.sync="rssModalActive" :rss="selectedRss" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { RssOnAdd } from "../../store/Feed.interface";

import RssCategory from "@/components/feeds/RssCategory.vue";
import RssModal from "@/components/feeds/RssModal.vue";
import FollowButton from "@/components/feeds/FollowButton.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    RssCategory,
    RssModal,
    FollowButton
  }
})
export default class AddRss extends Vue {
  @feedModule.State rssList!: [];
  @feedModule.Action FETCH_RSS: any;
  @feedModule.Action FETCH_CATEGORY_LIST: any;
  @feedModule.Action FETCH_SEARCH_CATEGORY: any;

  inputText = "";

  rssModalActive = false;

  selectedRss: RssOnAdd | object = {};

  created() {
    this.FETCH_RSS();
    this.FETCH_CATEGORY_LIST();
  }

  openWeb(link: string) {
    window.open(link);
  }

  toArticleDetail(link: string) {
    console.log(link);
  }

  searchRss($event: KeyboardEvent) {
    if (this.inputText && this.inputText[0] === "#") {
      const chips = document.querySelectorAll(".category-chip");
      if (chips.length) {
        chips.forEach(el => el.classList.remove("green", "lighten-3"));
      }
      this.FETCH_SEARCH_CATEGORY(this.inputText.slice(1));
    }
    this.inputText = "";
    ($event.target as HTMLElement).blur();
  }

  selectRss(rss: RssOnAdd) {
    this.selectedRss = rss;
    this.rssModalActive = !this.rssModalActive;
  }
}
</script>

<style scoped>
.rss-img {
  width: 60%;
  height: 70px;
  border: 1px solid #eeeeee;
}

.rss-link {
  cursor: pointer;
}

.rss-link:hover {
  text-decoration: underline;
}

.article-title {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin: 5px 0px;
  cursor: pointer;
}

.article-title:hover {
  background-color: #eeeeee;
}

.rss-title {
  cursor: pointer;
}
</style>
