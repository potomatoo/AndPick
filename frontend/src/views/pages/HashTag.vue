<template>
  <v-container class="mt-15">
    <v-row>
      <v-col xs="12" sm="12" md="8" lg="8" xl="8">
        <span style="font-size: 45px; font-weight: bold"
          >"{{ $route.params.tagName }}"</span
        >의 검색결과 입니다.

        <div class="mt-10">
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
      </v-col>
      <v-col
        md="3"
        lg="3"
        xl="3"
        style="height: 500px; border-left: 1px solid rgb(226, 226, 226); position: sticky; top: 130px; height: 520px; overflow-y: auto; margin-left: 70px;"
      >
        <div style="margin-left: 20px; margin-top:50px">
          <h6 style="font-weight: bold;">
            추천키워드
          </h6>

          <div class="container row">
            <div class="ma-1" v-for="tag in allTagDir" :key="tag.tagName">
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
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Post, AllTag } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component
export default class HashTag extends Vue {
  @mypageModule.State allTagDir!: AllTag[];
  @mypageModule.State tagDir!: Post[];
  @mypageModule.State tagName!: string;
  @mypageModule.Mutation SELECT_TAGDIR: any;
  @mypageModule.Action FETCH_ALLTAGDIR: any;
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

  @Watch("$route", { immediate: true })
  fetchallTagDir() {
    this.FETCH_ALLTAGDIR();
  }

  @Watch("tagName", { immediate: true })
  fetchTagDir() {
    this.FETCH_TAGDIR(this.tagName);
  }
}
</script>

<style scoped>
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
