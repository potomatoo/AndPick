<template>
  <div class="container">
    <div class="row">
      <h6 class="text-secondary font-weight-light mb-3">Mypage</h6>
    </div>

    <div class="row">
      <h1 class="pl-0 font-weight-bold">{{ $route.params.pageName }}</h1>
      <router-link :to="{ name: 'EditArticle' }" class="router-link">
        <v-btn small outlined color="secondary" class="ml-5 mt-5">
          <v-icon left>mdi-plus</v-icon>New
        </v-btn>
      </router-link>
    </div>

    <div class="container">
      <div class="row">
        <draggable :list="folderList" :options="{animation:200}" class="row wrap sortable-list">
          <v-flex v-for="row in folderList" :key="row.title" class="sortable">
            <draggable
              :list="row.articleList"
              :group="{ name: 'row' }"
              class="row justify-content-start row-sm-12"
            >
              <v-flex
                v-for="item in row.articleList"
                :key="item.title"
                xs12
                sm6
                md4
                pa-3
                class="row-v"
              >
                <v-hover v-slot:default="{ hover }" open-delay="200">
                  <v-card id="cursor_test" :elevation="hover ? 16 : 2" height="350" max-width="350">
                    <v-card-text
                      class="font-weight-medium mt-12 text-center subtitle-1"
                    >{{ item.title }}</v-card-text>
                    <hr class="mt-0" />
                    <div class="text--primary text-left ml-3">
                      {{ item.content }}
                      <br />
                    </div>
                  </v-card>
                </v-hover>
              </v-flex>
            </draggable>
          </v-flex>
        </draggable>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

import draggable from "vuedraggable";
import { namespace } from "vuex-class";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    draggable
  }
})
export default class FolderMain extends Vue {
  @mypageModule.State folderList!: [];

  click() {
    console.log("click");
  }
}
</script>

<style scoped>
#cursor_test {
  cursor: pointer;
}

.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}

#editbtn {
  outline: none;
}
</style>