package com.example.cookiy

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.cookiy.data.AppRepository
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.data.remote.RecipeApi
import kotlinx.coroutines.launch

//Tag für ErrorMessage, damit wir wissen wo es vorkommt
const val TAG = "MainViewModel"

//spezielle Klasse, innerhalb dieser Klasse können beschränkt werte vorliegen
enum class ApiStatus{ LOADING, ERROR, DONE }

/*erbt vom Androidviewmodel, wird verwendet um daten für die benutzeroberfläche bereitzustellen
und die Interaktion mit dem Repository zu koordinieren*/
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //erzeugt ein objekt von repository
    //eine Liste an Rezepten wird vom Repository bereitgestellt
    private val repository = AppRepository(RecipeApi, application)

    //laden livedata aus dem repository
    val recipes = repository.recipes

    //ruft die met aus repository auf und gibt die liste der favoriten zurück
    fun getAllFavorites() : List<Favorite> {
        return repository.getAllFavorites()
    }

    //anzahl der vorkommen des favoriten in der datenbank
    fun checkFavorite(favName: String) : Int {
        return repository.checkFavorite(favName)
    }

    //rezeptdaten werden geladen, wird in einer coroutine ausgeführt um die ui nicht zu blockieren
    init {
        loadRecipe()
    }

    //Funktion um neue Daten zu laden, wird in einer Coroutine abgerufen um UI nicht zu blockieren
    fun loadRecipe(){
        viewModelScope.launch {
            repository.getRecipe()
        }
    }

    //Funktionen aus dem Repository um fav zu löschen oder einzufügen
    fun insertFavorite(favoriteName: String){
        viewModelScope.launch {
            repository.insertFavorite(favoriteName)
        }
    }

    fun deleteFavorite(favoriteName: String){
        viewModelScope.launch {
            repository.deleteFavorite(favoriteName)
        }
    }


    //API CALL für die Bilder

    //Status des API Calls über Apistatus gezeigt
    //livedata wird erstellt, veränderbare
    private val _loading = MutableLiveData<ApiStatus>()
    //normale livedata, nicht veränderbare
    val loading: LiveData<ApiStatus>
        get() = _loading


    //wird ausgeführt wenn ein Instanz/Objekt von der Klasse MainViewModel erstellt wird
    init {
        loadData()
    }

    fun loadData() {
        //Coroutine wird gestartet
        viewModelScope.launch {
            //setzen den loading wert auf loading
            _loading.value = ApiStatus.LOADING

            // hat das loading funktioniert trycatch damit die App nicht abbricht
            try {
                //funktion getimages wird ausgeführt
                repository.getRecipe()
                //wenn es funktioniert hat, setzt den loading wert auf done
                _loading.value = ApiStatus.DONE
            } catch (e:Exception) {
                //wenn es nicht funktioniert hat, dann kommt eine fehler msg und wo
                Log.e(TAG, "Error loading data from api: $e")
                //setzen den loading wert auf error
                _loading.value = ApiStatus.ERROR
            }
        }
    }

    val category = repository.categoryList

}