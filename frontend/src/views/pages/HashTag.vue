<template>
  <div class="container mt-10">
    <h2 style="display: inline">"{{ $route.params.tagName }}"</h2>
    의 검색 결과입니다.
    <div class="container">
      <div class="row">
        <v-flex
          v-for="post in tagDir"
          :key="post.postId"
          xs12
          sm6
          md4
          pa-3
          class="row-v"
        >
          <v-hover v-slot:default="{ hover }">
            <router-link
              :to="{
                name: 'EditPost',
                params: {
                  postDirId: post.postDirId,
                  postId: post.postId
                }
              }"
              class="router-link"
            >
              <v-card
                id="cursor_test"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                height="350"
                max-width="350"
              >
                <v-card-text
                  class="font-weight-medium mt-12 text-center subtitle-1"
                  >{{ post.postTitle }}</v-card-text
                >
                <hr class="mt-0" />

                <div class="container row">
                  <div
                    class="ma-3"
                    v-for="tag in post.tagList"
                    :key="tag.postTagId"
                  >
                    <router-link
                      :to="{
                        name: 'HashTag',
                        params: {
                          tagName: tag.tagName
                        }
                      }"
                      class="router-link"
                    >
                      <v-btn rounded depressed>#{{ tag.tagName }}</v-btn>
                    </router-link>
                  </div>
                </div>
              </v-card>
            </router-link>
          </v-hover>
        </v-flex>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Post } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component
export default class HashTag extends Vue {
  @mypageModule.State tagDir!: Post[];
  @mypageModule.State tagName!: string;
  @mypageModule.Mutation SELECT_TAGDIR: any;
  @mypageModule.Action FETCH_TAGDIR: any;

  @Watch("$route", { immediate: true })
  selectTagDir() {
    this.SELECT_TAGDIR({
      tagName: this.$route.params.tagName
    });
  }

  @Watch("tagName", { immediate: true })
  fetchTagDir() {
    this.FETCH_TAGDIR(this.tagName);
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

#optionBtn {
  outline: none;
}
</style>
