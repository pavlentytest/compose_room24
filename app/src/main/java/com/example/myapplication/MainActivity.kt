package com.example.myapplication

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel


class UserViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(application) as T
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val viewModel: StudentViewModel = viewModel(
                    it,
                    "StudentViewModel",
                    UserViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                Main(viewModel)
            }
        }
    }
}


@Composable
fun Main(vm: StudentViewModel = viewModel()) {
    val userList by vm.studentList.observeAsState(listOf())
    Column {
        OutlinedTextField(vm.studentName, modifier= Modifier.padding(8.dp), label = { Text("Name") }, onValueChange = {vm.changeName(it)})
        OutlinedTextField(vm.studentAge.toString(), modifier= Modifier.padding(8.dp), label = { Text("Age") },
            onValueChange = {vm.changeAge(it)}
        )
        Button({ vm.addUser() }, Modifier.padding(8.dp)) {Text("Add", fontSize = 22.sp)}
        UserList(students = userList, delete = {vm.deleteUser(it)})

    }
}
@Composable
fun UserList(students:List<Student>, delete:(Int)->Unit) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item{ StudentTitleRow()}
        items(students) {s -> StudentRow(s, {delete(s.id)})  }
    }
}
@Composable
fun StudentRow(s:Student, delete:(Int)->Unit) {
    Row(Modifier.fillMaxWidth().padding(5.dp)) {
        Text(s.id.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(s.name, Modifier.weight(0.2f), fontSize = 22.sp)
        Text(s.age.toString(), Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Delete", Modifier.weight(0.2f).clickable { delete(s.id) }, color= Color.Red, fontSize = 22.sp)
    }
}
@Composable
fun StudentTitleRow() {
    Row(Modifier.background(Color.Gray).fillMaxWidth().padding(5.dp)) {
        Text("Id", color = Color.White,modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Name", color = Color.White,modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Age", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Spacer(Modifier.weight(0.2f))
    }
}