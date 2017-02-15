import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/map';
/**
 * Created by michal.zfania on 13-Feb-17.
 */

@Injectable()
export class CacheService{

  private CACHE_URL = 'cache';
  private LRU_CACHE_URL = 'lru/cache';

  constructor(private http : Http) {
  }

  public getAllCachedEntries(LRUmode: boolean) : any{
    return this.http.get(this.getURL(LRUmode))
      .map(this.extractJson, this)
      .catch(this.handleError);

  }

  public getCachedValue(key : string, LRUmode: boolean) : any{
    return this.http.get(this.getURL(LRUmode) + '/' +  key)
      .map(this.extractData, this)
      .catch(this.handleError);
  }

  public addEntryToCache(key : string, value : string, LRUmode: boolean){
    let requestBody = {};
    requestBody["key"] = key;
    requestBody["value"] = value;

    return this.http.post(this.getURL(LRUmode), requestBody)
      .catch(this.handleError);
  }

  private extractJson(res: Response) {
    return res.json();
  };

  private extractData(res: Response) {
    return res.text();
  };

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  private getURL(LRUMode: boolean) : any{
    if (LRUMode)
      return this.LRU_CACHE_URL;
    else
      return this.CACHE_URL;
  }

}
