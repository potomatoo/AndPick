<template>
  <div class="container">{{ nowData }}</div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class SelectFromOutside extends Vue {
  nowData = window.localStorage.getItem("postContent");
}
</script>
