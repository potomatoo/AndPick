<template>
  <div>
    <div style="margin-left: 10px; margin-top:40px">
      <div style="font-weight: bold;">
        카테고리 목록
      </div>

      <div class="container row">
        <div>
          <v-chip
            class="ma-2 category-chip"
            @click="selectAll($event)"
            color="green lighten-3"
          >
            #All
            <v-avatar right class="white">{{ rssAllCount }}</v-avatar>
          </v-chip>
        </div>
        <div v-for="category in categoryList" :key="category.categoryId">
          <v-chip
            class="ma-2 category-chip"
            @click="selectCategory(category.categoryName, $event)"
          >
            #{{ category.categoryName }}
            <v-avatar right class="white">
              {{ category.count }}
            </v-avatar>
          </v-chip>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const feedModule = namespace("feedModule");

@Component
export default class Category extends Vue {
  @feedModule.State categoryList!: Category[];
  @feedModule.State rssAllCount!: number;
  @feedModule.Action FETCH_SEARCH_CATEGORY: any;
  @feedModule.Action FETCH_RSS: any;

  selectCategory(categoryName: string, $event: MouseEvent) {
    const chips = document.querySelectorAll(".category-chip");
    if (chips.length) {
      chips.forEach(el => el.classList.remove("green", "lighten-3"));
    }
    ($event.currentTarget as HTMLElement).classList.add("green", "lighten-3");
    this.FETCH_SEARCH_CATEGORY(categoryName);
  }

  selectAll($event: MouseEvent) {
    const chips = document.querySelectorAll(".category-chip");
    if (chips.length) {
      chips.forEach(el => el.classList.remove("green", "lighten-3"));
    }
    ($event.currentTarget as HTMLElement).classList.add("green", "lighten-3");
    this.FETCH_RSS();
  }
}
</script>

<style scoped>
.category-chip {
  cursor: pointer;
  font-weight: medium;
}
</style>
