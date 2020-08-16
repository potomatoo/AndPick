<template>
  <div class="container mt-15">
    <h2 style="display: inline">"{{ $route.params.tagName }}"</h2>
    의 검색 결과입니다.

    <div class="container mt-10">
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
          <router-link
            :to="{
              name: 'EditPost',
              params: {
                postDirId: $route.params.postDirId,
                postId: post.postId
              }
            }"
            class="router-link"
          >
            <div class="container d-flex justify-content-around">
              <div
                align="center"
                class="post-box"
                style="border:5px solid #00d59b"
                @mouseenter="zoomIn"
                @mouseleave="zoomOut"
              >
                <div class="post-text pl-2 pr-2 mb-0 mt-3">
                  {{ post.postTitle }}
                  <hr style="border-color: #00d59b" />

                  <div class="container row">
                    <div
                      class="ma-1"
                      v-for="tag in post.tagList"
                      :key="tag.tagId"
                    >
                      <router-link
                        class="router-link"
                        :to="{
                          name: 'HashTag',
                          params: {
                            tagName: tag.tagName
                          }
                        }"
                      >
                        <v-btn
                          rounded
                          depressed
                          small
                          style="max-width: 200px"
                          id="HashTagbtn"
                          >#{{ tag.tagName }}</v-btn
                        >
                      </router-link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </router-link>
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

  zoomIn(event: any) {
    event.target.style.transform = "scale(1.1)";
    event.target.style.zIndex = 1;
    event.target.style.transition = "all 0.5s";
  }

  zoomOut(event: any) {
    event.target.style.transform = "scale(1)";
    event.target.style.zIndex = 0;
    event.target.style.transition = "all 0.5s";
  }

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

.router-link {
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none;
}

.post-box {
  cursor: pointer;
  width: 280px;
  height: 350px;
  margin: auto;
  white-space: nowrap;
  overflow: hidden;
  word-break: keep-all;
  text-overflow: ellipsis;
  border-radius: 25px;
}

.post-text {
  font-size: 25px;
  /* font-weight: 900; */
  margin-top: 50px;
  margin-bottom: 45px;
  /* transition: font-size 2s; */
  font-family: "Black Han Sans", sans-serif;
  /* font-family: "Jua", sans-serif; */
  white-space: nowrap;
  overflow: hidden;
  word-break: keep-all;
  text-overflow: ellipsis;
}

.v-btn__content {
  white-space: nowrap;
  overflow: hidden;
  word-break: keep-all;
  text-overflow: ellipsis;
}

.postTitle {
  text-overflow: ellipsis;
}
</style>
