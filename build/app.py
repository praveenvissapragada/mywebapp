import redis
from flask import Flask, request

app = Flask(__name__)
redis_client = redis.Redis(host='redis', port=6379, db=0)

@app.route('/')
def get_public_ip():
    ip = request.environ.get('HTTP_X_REAL_IP', request.remote_addr)
    ip_parts = ip.split('.')
    reversed_ip = '.'.join(reversed(ip_parts))
    
    # Write the IP address to Redis
    redis_client.set('public_ip', reversed_ip)
    
    return f"Your public IP address (reversed) is: {reversed_ip}"

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
