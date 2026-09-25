package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.ChatMessageEntity
import com.example.data.local.ProjectEntity
import com.example.data.local.UserProfileEntity
import com.example.data.repository.GojoRepository
import com.example.ui.components.NavigationTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GojoViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    val repository = GojoRepository(database)

    // Current navigation tab
    private val _currentTab = MutableStateFlow(NavigationTab.DASHBOARD)
    val currentTab: StateFlow<NavigationTab> = _currentTab.asStateFlow()

    // Auth screen state
    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _isSignUpMode = MutableStateFlow(false)
    val isSignUpMode: StateFlow<Boolean> = _isSignUpMode.asStateFlow()

    private val _authName = MutableStateFlow("رضا")
    val authName: StateFlow<String> = _authName.asStateFlow()

    private val _authEmail = MutableStateFlow("reza@example.com")
    val authEmail: StateFlow<String> = _authEmail.asStateFlow()

    private val _authPassword = MutableStateFlow("••••••••")
    val authPassword: StateFlow<String> = _authPassword.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    // Chat UI state
    val chatMessages: StateFlow<List<ChatMessageEntity>> = repository.chatMessages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _chatInput = MutableStateFlow("")
    val chatInput: StateFlow<String> = _chatInput.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val _attachedImageBase64 = MutableStateFlow<String?>(null)
    val attachedImageBase64: StateFlow<String?> = _attachedImageBase64.asStateFlow()

    // Projects state
    val allProjects: StateFlow<List<ProjectEntity>> = repository.allProjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _projectFilter = MutableStateFlow("همه")
    val projectFilter: StateFlow<String> = _projectFilter.asStateFlow()

    private val _selectedProject = MutableStateFlow<ProjectEntity?>(null)
    val selectedProject: StateFlow<ProjectEntity?> = _selectedProject.asStateFlow()

    // User profile state
    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // UI Dialogs
    private val _showNewProjectDialog = MutableStateFlow(false)
    val showNewProjectDialog: StateFlow<Boolean> = _showNewProjectDialog.asStateFlow()

    private val _showApiKeyDialog = MutableStateFlow(false)
    val showApiKeyDialog: StateFlow<Boolean> = _showApiKeyDialog.asStateFlow()

    private val _apiKeyInput = MutableStateFlow("")
    val apiKeyInput: StateFlow<String> = _apiKeyInput.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeDefaults()
        }
    }

    fun setTab(tab: NavigationTab) {
        _currentTab.value = tab
    }

    fun toggleAuthMode() {
        _isSignUpMode.value = !_isSignUpMode.value
        _authError.value = null
    }

    fun setAuthName(name: String) { _authName.value = name }
    fun setAuthEmail(email: String) { _authEmail.value = email }
    fun setAuthPassword(pass: String) { _authPassword.value = pass }

    fun submitAuth() {
        if (_isSignUpMode.value && _authName.value.isBlank()) {
            _authError.value = "لطفاً نام خود را وارد کنید"
            return
        }
        if (_authEmail.value.isBlank() || !_authEmail.value.contains("@")) {
            _authError.value = "لطفاً یک ایمیل معتبر وارد کنید"
            return
        }
        if (_authPassword.value.length < 4) {
            _authError.value = "رمز عبور باید حداقل ۴ حرف باشد"
            return
        }

        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            val newProfile = current.copy(
                name = if (_authName.value.isNotBlank()) _authName.value else current.name,
                email = _authEmail.value,
                isLoggedIn = true
            )
            repository.updateUserProfile(newProfile)
            _isLoggedIn.value = true
            _authError.value = null
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.setLoginStatus(false)
            _isLoggedIn.value = false
        }
    }

    fun setChatInput(text: String) {
        _chatInput.value = text
    }

    fun setAttachedImage(base64: String?) {
        _attachedImageBase64.value = base64
    }

    fun sendMessage(customPrompt: String? = null) {
        val prompt = customPrompt ?: _chatInput.value.trim()
        if (prompt.isBlank() && _attachedImageBase64.value == null) return

        val image = _attachedImageBase64.value
        _chatInput.value = ""
        _attachedImageBase64.value = null
        _isGenerating.value = true

        viewModelScope.launch {
            try {
                repository.sendMessage(prompt, image)
            } catch (e: Exception) {
                _toastMessage.value = "خطا در ارسال پیام: ${e.message}"
            } finally {
                _isGenerating.value = false
            }
        }
    }

    fun sendQuickPrompt(promptText: String) {
        _currentTab.value = NavigationTab.CHAT
        sendMessage(promptText)
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            repository.clearChat()
            _toastMessage.value = "تاریخچه گفتگو پاک شد"
        }
    }

    fun updateProfile(name: String, email: String) {
        viewModelScope.launch {
            val cur = userProfile.value ?: UserProfileEntity()
            repository.updateUserProfile(cur.copy(name = name, email = email))
            _toastMessage.value = "اطلاعات با موفقیت ذخیره شد"
        }
    }

    fun setProjectFilter(filter: String) {
        _projectFilter.value = filter
    }

    fun selectProject(project: ProjectEntity?) {
        _selectedProject.value = project
    }

    fun openNewProjectDialog() {
        _showNewProjectDialog.value = true
    }

    fun closeNewProjectDialog() {
        _showNewProjectDialog.value = false
    }

    fun createProject(title: String, description: String, category: String, colorHex: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            val newProject = ProjectEntity(
                title = title,
                description = description,
                category = category,
                colorHex = colorHex,
                content = "پروژه ایجاد شد. برای تولید محتوا یا تحلیل با Gojo AI کلیک کنید.",
                lastModified = System.currentTimeMillis()
            )
            repository.insertProject(newProject)
            _showNewProjectDialog.value = false
            _toastMessage.value = "پروژه «$title» با موفقیت افزوده شد"
        }
    }

    fun updateProjectContent(project: ProjectEntity, newContent: String) {
        viewModelScope.launch {
            repository.updateProject(
                project.copy(
                    content = newContent,
                    lastModified = System.currentTimeMillis()
                )
            )
            _selectedProject.value = project.copy(content = newContent)
            _toastMessage.value = "تغییرات ذخیره شد"
        }
    }

    fun deleteProject(project: ProjectEntity) {
        viewModelScope.launch {
            repository.deleteProject(project)
            _selectedProject.value = null
            _toastMessage.value = "پروژه حذف شد"
        }
    }

    fun openApiKeyDialog() {
        _apiKeyInput.value = userProfile.value?.customApiKey.orEmpty()
        _showApiKeyDialog.value = true
    }

    fun closeApiKeyDialog() {
        _showApiKeyDialog.value = false
    }

    fun saveApiKey(newKey: String) {
        viewModelScope.launch {
            repository.setCustomApiKey(newKey.trim())
            _showApiKeyDialog.value = false
            _toastMessage.value = if (newKey.isNotBlank()) "کلید API ذخیره شد" else "کلید اختصاصی حذف شد"
        }
    }

    fun clearToast() {
        _toastMessage.value = null
    }
}
