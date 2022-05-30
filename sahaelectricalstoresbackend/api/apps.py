from django.apps import AppConfig
import os

class ApiConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'api'
    def ready(self):
        if os.environ.get('RUN_MAIN'):
            print('check')
            from api import dbManager
            dbManager.main()
