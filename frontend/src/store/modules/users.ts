import {
  VuexModule,
  Module,
  getModule,
  Mutation,
  Action,
} from "vuex-module-decorators";
import store from "@/store";
import { User, UserSubmit } from "../models";
import { loginUser } from "../api";

@Module({
  namespaced: true,
  name: "users",
  store,
  dynamic: true,
})
class UsersModule extends VuexModule {
  user: User | null = null;

  @Mutation
  setUser(user: User) {
    this.user = user;
  }

  @Action({ commit: "setUser" })
  async login(userSubmit: UserSubmit) {
    const user = await loginUser(userSubmit);
    return { user: user };
  }
}

export default getModule(UsersModule);
