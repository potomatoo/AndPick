<template>
  <div class="container mt-5">
    <div class="row">
      <ServiceDescription />
      <RssImage />
    </div>
    <UserInformation />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import ServiceDescription from "@/components/cover/ServiceDescription.vue";
import RssImage from "@/components/cover/RssImage.vue";
import UserInformation from "@/components/cover/UserInformation.vue";

@Component({
  components: {
    ServiceDescription,
    RssImage,
    UserInformation,
  },
})
export default class Cover extends Vue {}
</script>

<style></style>
