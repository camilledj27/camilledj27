package com.example.feelingsdiary


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_profile2.*
import java.io.IOException


private var mAuth: FirebaseAuth? = null
private var textViewemailname: TextView? = null
private var mDatabaseReference: DatabaseReference? = null
private var mProfileFirstName: TextView? = null
private var mProfileLastName: TextView? = null
private var mProfileBday: TextView? = null
private var mProfileEmail: TextView? = null
private var profileImageView: ImageView? = null
private var FNBtn: Button? = null
private var LNBtn: Button? = null
private var BDAYBtn: Button? = null
private const val PICK_IMAGE = 123
var imagePath: Uri? = null

private lateinit var mDatabase: FirebaseDatabase


class ProfilePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile2)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase.getReference("Users")

        //mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mProfileFirstName = findViewById(R.id.profile_first_name);
        mProfileLastName = findViewById(R.id.profile_last_name);
        mProfileEmail = findViewById(R.id.profile_email);

        mProfileBday = findViewById(R.id.profile_bday);
        FNBtn = findViewById(R.id.buttonEditFirstName);
        LNBtn = findViewById(R.id.buttonEditLastName);
        BDAYBtn = findViewById(R.id.buttonEditBday);

        profileImageView = findViewById(R.id.profile_pic_imageView);

        profileImageView?.setOnClickListener{

                val profileIntent = Intent()
                profileIntent.type = "image/*"
                profileIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(profileIntent, "Select Image."),
                    PICK_IMAGE
                )

        }

        val intent = intent

        mProfileEmail?.text = intent.getStringExtra("user_email").toString()

        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === PICK_IMAGE && resultCode === Activity.RESULT_OK && data?.getData() != null) {
            imagePath = data?.getData()
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, imagePath)
                profileImageView!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun EditFirstName(view: View?) {
        val inflater = layoutInflater
        val alertLayout: View =
            inflater.inflate(R.layout.layout_custom_dialog_edit_name, null)
        val etUsername = alertLayout.findViewById<EditText>(R.id.et_username)
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Edit First Name")
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout)
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false)
        alert.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> })
        alert.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                val name = etUsername.text.toString()
                val lastname: String = profile_last_name.getText().toString()
                val bday: String = profile_bday.getText().toString()
                mProfileFirstName?.text = etUsername.text.toString()
//                val userinformation = User(name, lastname, bday)
//                val user: FirebaseUser = firebaseAuth.getCurrentUser()
//                databaseReference.child(user.uid).setValue(userinformation)
//                databaseReference.child(user.uid).setValue(userinformation)
                etUsername.onEditorAction(EditorInfo.IME_ACTION_DONE)
            })
        val dialog: AlertDialog = alert.create()
        dialog.show()
    }

    fun EditLastName(view: View?) {
        val inflater = layoutInflater
        val alertLayout: View =
            inflater.inflate(R.layout.layout_custom_dialog_edit_name, null)
        val etUsername = alertLayout.findViewById<EditText>(R.id.et_username)
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Edit Last Name")
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout)
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false)
        alert.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> })
        alert.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                val name = etUsername.text.toString()
                val lastname: String = profile_last_name.getText().toString()
                val bday: String = profile_bday.getText().toString()
                mProfileLastName?.text = etUsername.text.toString()
//                val userinformation = User(name, lastname, bday)
//                val user: FirebaseUser = firebaseAuth.getCurrentUser()
//                databaseReference.child(user.uid).setValue(userinformation)
//                databaseReference.child(user.uid).setValue(userinformation)
                etUsername.onEditorAction(EditorInfo.IME_ACTION_DONE)
            })
        val dialog: AlertDialog = alert.create()
        dialog.show()
    }

    fun EditBday(view: View?) {
        val inflater = layoutInflater
        val alertLayout: View =
            inflater.inflate(R.layout.layout_custom_dialog_edit_name, null)
        val etUsername = alertLayout.findViewById<EditText>(R.id.et_username)
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Edit Birthday")
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout)
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false)
        alert.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> })
        alert.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                val name = etUsername.text.toString()
                val lastname: String = profile_last_name.getText().toString()
                val bday: String = profile_bday.getText().toString()
                mProfileBday?.text = etUsername.text.toString()
//                val userinformation = User(name, lastname, bday)
//                val user: FirebaseUser = firebaseAuth.getCurrentUser()
//                databaseReference.child(user.uid).setValue(userinformation)
//                databaseReference.child(user.uid).setValue(userinformation)
                etUsername.onEditorAction(EditorInfo.IME_ACTION_DONE)
            })
        val dialog: AlertDialog = alert.create()
        dialog.show()
    }

    fun LogOut(v: View?) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            loadFragment(item.itemId)
            true
        }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        var fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.home -> {
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            }
            R.id.profile -> {
                val intent = Intent(this, ProfilePageActivity::class.java)
                startActivity(intent)
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
            }
            R.id.calendar -> {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
                Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
            }
            R.id.diary -> {
                val intent = Intent(this,DashboardActivity::class.java )
                startActivity(intent)
                Toast.makeText(getApplicationContext(), "Diary", Toast.LENGTH_SHORT).show();
            }
            else -> {
                null
            }
        }


    }
}