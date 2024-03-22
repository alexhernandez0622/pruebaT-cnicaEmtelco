from datetime import datetime, timedelta

def generar_menu(fecha, hora):
    # Definir horario hábil
    horario_habil = {
        "Monday": range(8, 19),
        "Tuesday": range(8, 19),
        "Wednesday": range(8, 19),
        "Thursday": range(8, 19),
        "Friday": range(8, 19),
        "Saturday": range(8, 13)
    }

    # Obtener el día de la semana
    dia_semana = fecha.strftime("%A")

    # Verificar si la hora proporcionada está dentro del horario hábil
    if hora.hour < 8 or hora.hour > 18 or hora.hour not in horario_habil[dia_semana]:
        print("La hora proporcionada no está dentro del horario de funcionamiento.")
        return

    # Mostrar el menú de horarios disponibles
    print("Menú de horarios disponibles para las próximas 24 horas:")
    for i in range(24):
        nueva_fecha = fecha + timedelta(hours=i)
        # Si es el mismo día, mostrar solo las horas restantes del día
        if nueva_fecha.date() == fecha:
            if nueva_fecha.hour in horario_habil[dia_semana]:
                print(f"{nueva_fecha.strftime('%d de %B de %Y')} de {nueva_fecha.strftime('%I:%M %p')} a {nueva_fecha.replace(hour=nueva_fecha.hour+1).strftime('%I:%M %p')}.")
        # Si es el siguiente día, mostrar las horas desde las 7am
        elif nueva_fecha.hour == 8:
            print(f"{nueva_fecha.strftime('%d de %B de %Y')} de {nueva_fecha.strftime('%I:%M %p')} a {nueva_fecha.replace(hour=nueva_fecha.hour+1).strftime('%I:%M %p')}.")

# Función para obtener la fecha y hora ingresadas por el usuario
def obtener_fecha_hora():
    fecha_str = input("Ingrese la fecha (formato dd/mm/yyyy): ")
    hora_str = input("Ingrese la hora (formato hh:mm): ")
    try:
        fecha = datetime.strptime(fecha_str, "%d/%m/%Y")
        hora = datetime.strptime(hora_str, "%H:%M").time()
        return fecha, hora
    except ValueError:
        print("Formato de fecha u hora incorrecto. Por favor, inténtelo de nuevo.")
        return None, None

# Función para mostrar el menú de opciones
def mostrar_menu():
    print("Bienvenido al sistema de reservas automatizadas.")
    print("Por favor, ingrese la fecha y hora para ver los horarios disponibles en las próximas 24 horas.")
    fecha, hora = obtener_fecha_hora()
    if fecha and hora:
        generar_menu(fecha, hora)

# Ejecutar el programa
mostrar_menu()
