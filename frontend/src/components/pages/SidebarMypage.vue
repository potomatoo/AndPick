<template>
  <div>
    <v-divider></v-divider>
    <v-list>
      <v-subheader>Mypage</v-subheader>
      <v-list-item v-for="(item, i) in mypageList" :key="i" @click="click">
        <v-list-item-icon>
          <v-icon v-text="item.icon"></v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="item.title"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

interface MypageList {
  title: string;
  icon: string;
}

@Component
export default class SidebarMypage extends Vue {
  mypageList: MypageList[] = [
    { title: "My list", icon: "mdi-alpha-l-box" },
    { title: "New Post", icon: "mdi-alpha-n-box" },
    { title: "logout", icon: "mdi-alpha-l-box" }
  ];
  click() {
    console.log("click");
  }
}
</script>
