import {Component} from '@angular/core';
import {CacheService} from "./cache.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers : [CacheService]
})
export class AppComponent {
  private errorMessages;
  private allCacheEntries;
  private valueByKey :  string;
  private LRUMode : boolean = false;
  constructor(private cacheService : CacheService) {
  }

  private addToCache(key : string, value : string){
    if (!this.isValid(key) || !this.isValid(value)){
      this.errorMessages = "invalid key or value entered";
      return;
    }

    this.cacheService.addEntryToCache(key, value, this.LRUMode).subscribe(() => {
      //added just for view purposes.
      this.getAllCachedEntries();
    })
  }

  private getAllCachedEntries(){
    this.clearErrorMessage()
    this.cacheService.getAllCachedEntries(this.LRUMode).subscribe(allCachedEntries => {
      this.allCacheEntries = allCachedEntries;
    });
  }

  private getCachedValue(key : string){
    this.valueByKey = "";
    if (!this.isValid(key)){
      this.errorMessages = "illegal argument.";
      return;
    }

    this.cacheService.getCachedValue(key, this.LRUMode).subscribe(value => {
      if (value){
        this.valueByKey = value;
        this.clearErrorMessage();
      }else{
        this.errorMessages = "Key Does Not Exists";
      }
    });
  }

  private isValid(str: string) {
    return str != null && str.length != 0;
  }

  private clearErrorMessage(){
    this.errorMessages = "";
  }
}
