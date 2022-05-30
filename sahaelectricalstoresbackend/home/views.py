from django.shortcuts import render , HttpResponse

# Create your views here.
from django.http import *
import os
import mimetypes

def home(request):
   return render(request, 'home.html')

def download(request,name):
   BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
   destination =  str(BASE_DIR) + '\\Downloads\\'
   filepath = destination + name
   response = HttpResponse(open(filepath, 'rb').read())
   mime_type, _ = mimetypes.guess_type(filepath)
   response['Content-Disposition'] = "attachment; filename=%s" % name
   return response