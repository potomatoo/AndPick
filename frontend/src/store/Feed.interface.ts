export interface SidebarItem {
  title: string;
  url?: string;
}

export interface SidebarList {
  title: string;
  icon?: string;
  active?: boolean;
  items?: SidebarItem[];
}

export interface FeedModule {
  rssList: RssList[];
  feedList: SidebarList[];
  boardList: SidebarList[];
}

export interface RssList {
  title: string;
  url: string;
}
