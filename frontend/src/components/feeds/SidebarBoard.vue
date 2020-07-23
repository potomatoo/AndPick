<template>
  <div>
    <v-divider></v-divider>
    <v-list>
      <v-subheader>Board</v-subheader>
      <v-list-group
        v-for="item in boardList"
        :key="item.title"
        v-model="item.active"
        :prepend-icon="item.action"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item v-for="subItem in item.items" :key="subItem.title" @click="click">
          <v-list-item-content>
            <v-list-item-title v-text="subItem.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

interface BoardItem {
  title: string;
}

interface BoardList {
  title: string;
  icon: string;
  active: boolean;
  items: BoardItem[];
}

@Component
export default class SidebarMypage extends Vue {
  boardList: BoardList[] = [
    {
      title: "Samsung",
      icon: "mdi-alpha-s-box",
      active: true,
      items: [{ title: "list item" }, { title: "hi" }]
    },
    {
      title: "Nasa",
      icon: "mdi-alpha-n-box",
      active: false,
      items: [{ title: "list item" }, { title: "hi" }]
    },
    {
      title: "Tesla",
      icon: "mdi-alpha-t-box",
      active: false,
      items: [{ title: "list item" }, { title: "hi" }]
    }
  ];

  click() {
    console.log("click");
  }
}
</script>
