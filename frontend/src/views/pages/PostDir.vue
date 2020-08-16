<template>
  <div class="container" v-if="postDir">
    <div class="row">
      <h6 class="pl-3 text-secondary font-weight-light mb-3 col-10">Mypage</h6>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn id="optionBtn" small color="white" class="mt-3">
            <v-icon v-bind="attrs" v-on="on">mdi-menu</v-icon>
          </v-btn>
        </template>
        <v-list dense>
          <v-list-item @click="click">
            <v-list-item-title>Latest</v-list-item-title>
          </v-list-item>
          <v-list-item @click="click">
            <v-list-item-title>Oldest</v-list-item-title>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="deletePostDir()">
            <v-list-item-title>
              <v-icon small left>mdi-delete</v-icon>Delete
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>

    <div class="row">
      <h1 class="pl-3 font-weight-bold">{{ postDir.postDirName }}</h1>
    </div>
    <v-divider></v-divider>

    <router-link
      :to="{ name: 'NewPost', params: { postDirId: $route.params.postDirId } }"
      class="router-link"
    >
      <v-btn
        small
        outlined
        color="secondary"
        v-if="postDir.postList && postDir.postList.length"
      >
        <v-icon left>mdi-plus</v-icon>New
      </v-btn>
    </router-link>

    <div
      v-if="postDir.postList && !postDir.postList.length"
      class="text-center"
    >
      <v-icon style="font-size: 180px">mdi-comment-plus-outline</v-icon>
      <h4 class="mt-10">
        Save post to here
      </h4>

      <p class="text-center">
        When you find an news in your Subscribe you want to keep and editing
        with your think, click
        <router-link
          :to="{
            name: 'NewPost',
            params: { postDirId: $route.params.postDirId }
          }"
          class="router-link"
        >
          <v-btn small outlined color="secondary" class>
            <v-icon left>mdi-plus</v-icon>New
          </v-btn>
        </router-link>
      </p>
    </div>

    <div class="container">
      <div class="row">
        <v-flex
          v-for="post in postDir.postList"
          :key="post.postId"
          xs12
          sm6
          md4
          pa-3
          class="row-v"
          @contextmenu.prevent="showPostCtx($event, post)"
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
                style="border:5px solid #ad249f"
                @mouseenter="zoomIn"
                @mouseleave="zoomOut"
              >
                <div class="post-text pl-2 pr-2 mb-0 mt-3">
                  {{ post.postTitle }}
                  <hr style="border-color: #ad249f" />

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

        <post-context-menu :postItem="postItem" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";

import draggable from "vuedraggable";
import { namespace } from "vuex-class";
import PostContextMenu from "@/components/pages/PostContextMenu.vue";
import { Post } from "../../store/MypageInterface";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    PostContextMenu
  }
})
export default class FolderMain extends Vue {
  @mypageModule.State postDir!: [];
  @mypageModule.State postDirId!: number | null;
  @mypageModule.State postDirName!: string | null;
  @mypageModule.Mutation SELECT_POSTDIR: any;
  @mypageModule.Mutation SET_POST_CONTEXT_MENU: any;
  @mypageModule.Action FETCH_POSTDIR: any;
  @mypageModule.Action DELETE_POSTDIR: any;
  @mypageModule.Action UPDATE_POSTDIR: any;

  click() {
    console.log("click");
  }

  postItem = {};

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

  showPostCtx(e: MouseEvent, post: Post) {
    this.postItem = post;

    const ctx = {
      showCtx: true,
      x: e.clientX,
      y: e.clientY
    };
    this.SET_POST_CONTEXT_MENU(ctx);
  }

  routeHashTag(tagName: string) {
    this.$router.push({ name: "HashTag", params: { tagName: tagName } });
  }

  deletePostDir() {
    this.DELETE_POSTDIR(this.$route.params.postDirId);
  }

  @Watch("$route", { immediate: true })
  selectPostDir() {
    this.SELECT_POSTDIR({
      postDirId: this.$route.params.postDirId
    });
  }

  @Watch("postDirId", { immediate: true })
  fetchPostDir() {
    this.FETCH_POSTDIR(this.postDirId);
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

/* .step-text:hover {
  font-size: 50px;
} */

#editbtn {
  outline: none;
}

#optionBtn {
  outline: none;
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
