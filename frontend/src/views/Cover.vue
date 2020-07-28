<template>
  <div class="container">
    <div class="row">
      <ServiceDescription />
      <RssImage />
      <UserInformation />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import ServiceDescription from "@/components/cover/ServiceDescrition.vue";
import RssImage from "@/components/cover/RssImage.vue";
import UserInformation from "@/components/cover/UserInformation.vue";

@Component
export default class Cover extends Vue {}
</script>

<style></style>
